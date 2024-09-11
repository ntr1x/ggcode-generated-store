local Structure = require('vue/Structure')

local projects = require('vue/definitions/projects')

local structures = {}

-- Declarations

structures.structure_with_id_record = Structure
  :from_object(projects.project, 'width_id_record')

structures.structure_public_category_record = Structure
  :from_object(projects.project, 'public_category_record')

structures.structure_public_product_record = Structure
  :from_object(projects.project, 'public_product_record')

structures.structure_public_guide_subject_record = Structure
  :from_object(projects.project, 'public_guide_subject_record')

structures.structure_public_guide_record = Structure
  :from_object(projects.project, 'public_guide_record')

structures.structure_public_dispatch_record = Structure
  :from_object(projects.project, 'public_dispatch_record')

structures.structure_public_network_record = Structure
  :from_object(projects.project, 'public_network_record')

-- Records

structures.structure_with_id_record:with_rules {
  Structure.Field:from_any('id'):with_required(),
}

structures.structure_public_category_record:with_rules {
  Structure.Field:from_string('id'):with_required(),
  Structure.Field:from_string('name'),
  Structure.Field:from_string('description'),
}

structures.structure_public_product_record:with_rules {
  Structure.Field:from_string('id'):with_required(),
  Structure.Field:from_string('name'),
  Structure.Field:from_array('tags', 'string'),
  Structure.Field:from_string('description'),
  Structure.Field:from_string('price'),
  Structure.Field:from_string('ean13'),
  Structure.Field:from_string('categoryId'),
  Structure.Field:from_object('category', nil, structures.structure_public_category_record),
}

structures.structure_public_guide_subject_record:with_rules {
  Structure.Field:from_string('id'):with_required(),
  Structure.Field:from_string('name'),
  Structure.Field:from_number('position'),
}

structures.structure_public_guide_record:with_rules {
  Structure.Field:from_string('id'):with_required(),
  Structure.Field:from_string('name'),
  Structure.Field:from_number('position'),
  Structure.Field:from_string('description'),
}

structures.structure_public_dispatch_record:with_rules {
  Structure.Field:from_string('id'):with_required(),
}

structures.structure_public_network_record:with_rules {
  Structure.Field:from_string('id'):with_required(),
}

-- Arrays

structures.structure_public_category_array = Structure
  :from_array(projects.project, 'public_category_array', nil, structures.structure_public_category_record)

structures.structure_public_product_array = Structure
  :from_array(projects.project, 'public_product_array', nil, structures.structure_public_product_record)

-- Pages

structures.structure_public_product_page = Structure
  :from_object(projects.project, 'public_product_page')
  :with_rules {
    Structure.Field:from_array('content', nil, structures.structure_public_product_record):with_required(),
  }

structures.structure_public_category_page = Structure
  :from_object(projects.project, 'public_category_page')
  :with_rules {
    Structure.Field:from_array('content', nil, structures.structure_public_category_record):with_required(),
  }

structures.structure_public_guide_page = Structure
  :from_object(projects.project, 'public_guide_page')
  :with_rules {
    Structure.Field:from_array('content', nil, structures.structure_public_guide_record):with_required(),
  }

structures.structure_public_guide_subject_page = Structure
  :from_object(projects.project, 'public_guide_subject_page')
  :with_rules {
    Structure.Field:from_array('content', nil, structures.structure_public_guide_subject_record):with_required(),
  }

structures.structure_public_network_page = Structure
  :from_object(projects.project, 'public_network_page')
  :with_rules {
    Structure.Field:from_array('content', nil, structures.structure_public_network_record):with_required(),
  }

structures.structure_public_dispatch_page = Structure
  :from_object(projects.project, 'public_dispatch_page')
  :with_rules {
    Structure.Field:from_array('content', nil, structures.structure_public_dispatch_record):with_required(),
  }

return structures
